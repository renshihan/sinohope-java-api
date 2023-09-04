package com.sinohope.client.impl;

import com.sinohope.client.TransactionClient;
import com.sinohope.request.SignatureWAASResult;
import com.sinohope.request.TransferHistoryWAASDTO;
import com.sinohope.request.WalletTransactionCancelWAASParam;
import com.sinohope.request.WalletTransactionQueryWAASParam;
import com.sinohope.request.WalletTransactionQueryWAASRequestIdParam;
import com.sinohope.request.WalletTransactionQueryWAASSinoIdParam;
import com.sinohope.request.WalletTransactionQueryWAASTxHashdParam;
import com.sinohope.request.WalletTransactionSendDataWAASParam;
import com.sinohope.request.WalletTransactionSendWAASParam;
import com.sinohope.request.WalletTransactionSpeedupWAASParam;
import com.sinohope.request.signMessageParam;
import com.sinohope.response.CreateSettlementTxRes;
import com.sinohope.response.common.PageData;
import com.sinohope.response.common.ResultData;
import com.sinohope.service.TransactionService;
import com.sinohope.sign.ECDSA;

import java.util.List;

import static com.sinohope.service.ApiServiceGenerator.createService;
import static com.sinohope.service.ApiServiceGenerator.executeSync;

/**
 * @Author sunlf
 * @Date 2023/8/15
 */
public class TransactionClientImpl implements TransactionClient {
  private final TransactionService transactionService;

  public TransactionClientImpl(ECDSA signer, String env, String publicKey, String privateKey) {
    transactionService = createService(TransactionService.class, signer, env, publicKey, privateKey);
  }

  @Override
  public ResultData<PageData<TransferHistoryWAASDTO>> walletTransactionList(WalletTransactionQueryWAASParam param) {
    return executeSync(transactionService.walletTransactionList(param));
  }

  @Override
  public ResultData<List<TransferHistoryWAASDTO>> walletTransactionListByRequestId(WalletTransactionQueryWAASRequestIdParam param) {
    return executeSync(transactionService.walletTransactionListByRequestId(param));
  }

  @Override
  public ResultData<List<TransferHistoryWAASDTO>> walletTransactionListByTransactionId(WalletTransactionQueryWAASSinoIdParam sinoIds) {
    return executeSync(transactionService.walletTransactionListByTransactionId(sinoIds));
  }

  @Override
  public ResultData<List<TransferHistoryWAASDTO>> walletTransactionListByTxHash(WalletTransactionQueryWAASTxHashdParam txHash) {
    return executeSync(transactionService.walletTransactionListByTxHash(txHash));
  }

  @Override
  public ResultData<CreateSettlementTxRes> createWaasTransfer(WalletTransactionSendWAASParam param) {
    return executeSync(transactionService.createWaasTransfer(param));
  }

  @Override
  public ResultData<CreateSettlementTxRes> speedupWaasTransfer(WalletTransactionSpeedupWAASParam param) {
    return executeSync(transactionService.speedupWaasTransfer(param));
  }

  @Override
  public ResultData<CreateSettlementTxRes> waasCancelTransaction(WalletTransactionCancelWAASParam param) {
    return executeSync(transactionService.waasCancelTransaction(param));
  }

  @Override
  public ResultData<CreateSettlementTxRes> createWaasTransaction(WalletTransactionSendDataWAASParam param) {
    return executeSync(transactionService.createWaasTransaction(param));
  }

  @Override
  public ResultData<SignatureWAASResult> web3SignMessage(signMessageParam param) {
    return executeSync(transactionService.web3SignMessage(param));
  }
}